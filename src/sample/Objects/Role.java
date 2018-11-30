package sample.Objects;

import sample.SQL_Classes.SQLConnectionManager;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Role {
    private int role_id;

    private String roleName;
    private String roleCode;

    public String getRoleName() {
        return roleName;
    }
    public String getRoleCode() {
        return roleCode;
    }

    public static Role Create(Integer roleId) throws SQLException {

        Connection connection = SQLConnectionManager.getConnection();

        Role role = new Role();
        // TODO make role
        return role;
    }
}
