package com.mtech.DBManager;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;

import org.jooq.util.GenerationTool;
import org.jooq.util.jaxb.Configuration;

public class DBClassGen {

	private final String DB_URL = "jdbc:h2:./h2db/test;";
	private final String DB_INIT_SCRIPTS = "INIT=RUNSCRIPT FROM './h2db/sql/create.sql';";
	private final String DB_USER = "sa";
	private final String DB_PASSWORD = "";

	public static void main(String[] args) throws Exception {
		DBClassGen gen = new DBClassGen();
		gen.initDB();
		gen.generate();
	}

	private void initDB() throws Exception {
		Class.forName("org.h2.Driver");
		Connection conn = DriverManager.getConnection(DB_URL + DB_INIT_SCRIPTS,
				DB_USER, DB_PASSWORD);
		//
		conn.close();
	}

	private static void generate() throws Exception {
		GenerationTool tool = new GenerationTool();
		Configuration configuration = tool.load(new FileInputStream(new File(
				"conf.xml")));
		tool.run(configuration);
	}

}
