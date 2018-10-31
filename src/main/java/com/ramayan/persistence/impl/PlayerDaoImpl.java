package com.ramayan.persistence.impl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ramayan.model.Player;
import com.ramayan.persistence.PlayerDao;

public class PlayerDaoImpl implements PlayerDao {
	Connection dbConnection;

	public PlayerDaoImpl(Connection db) {
		this.dbConnection = db;
	}

	public List<Player> getUsers() {
		String sql = "Select * from players";
		try {

			ResultSet resultSet = dbConnection.createStatement().executeQuery(sql);
			List<Player> playersfromDB = new ArrayList<Player>();
			while (resultSet.next()) {
				Player chartData = null;
				try {
					ObjectInputStream in = new ObjectInputStream(
							new ByteArrayInputStream(resultSet.getBytes("player")));
					chartData = (Player) in.readObject();
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}

				playersfromDB.add(chartData);
			}
			return playersfromDB;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void persistUser(Player toSave) {
		String sql = "insert into players(name, player) values(?, ?)";
		try {
			PreparedStatement pstmt = dbConnection.prepareStatement(sql);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(baos);
			oos.writeObject(toSave);
			pstmt.setString(1, toSave.getName());
			pstmt.setBinaryStream(2, new ByteArrayInputStream(baos.toByteArray()));
			pstmt.executeUpdate();
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}

	}

	public void updateUser(Player player) {
		String sql = "update players set player=? where name=?";
		try {
			PreparedStatement pstmt = dbConnection.prepareStatement(sql);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(baos);
			oos.writeObject(player);
			pstmt.setBinaryStream(1, new ByteArrayInputStream(baos.toByteArray()));
			pstmt.setString(2, player.getName());
			pstmt.executeUpdate();
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteUser(String player) {
		String sql = "delete from players where name=?";
		try {
			PreparedStatement pstmt = dbConnection.prepareStatement(sql);
			pstmt.setString(1, player);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
