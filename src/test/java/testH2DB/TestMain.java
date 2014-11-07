package testH2DB;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.mapping;
import static java.util.stream.Collectors.toList;

import java.sql.Connection;
import java.sql.DriverManager;

import org.jooq.DSLContext;
import org.jooq.impl.DSL;

public class TestMain {
	public static void main(String[] a) throws Exception {
		Class.forName("org.h2.Driver");
		Connection conn = DriverManager.getConnection("jdbc:h2:./h2db/test",
				"sa", "");
		// add application code here
		DSLContext dslContext = DSL.using(conn);
		// This SQL statement produces all table
		// names and column names in the H2 schema
		String sql = "select table_name, column_name "
				+ "from information_schema.columns " + "order by "
				+ "table_catalog, " + "table_schema, " + "table_name, "
				+ "ordinal_position";

		// Result<Record> records = dslContext.select().from(Tables.CUSTOMER)
		// .fetch();
		DSL.using(conn)
		.fetch(sql)
		.stream()
		.collect(
				groupingBy(
						r -> r.getValue("TABLE_NAME"),
						mapping(r -> r.getValue("COLUMN_NAME"),
								toList())))
								.forEach(
										(table, columns) -> System.out.println(table + ": "
												+ columns));
		//
		conn.close();
	}
}
