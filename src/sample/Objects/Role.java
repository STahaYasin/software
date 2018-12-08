package sample.Objects;

import sample.SQL_Classes.SQLConnectionManager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Role {
    private int role_id;

    private String roleName;
    private String roleCode;

    public Role(){

    }
    public Role(int roleId){
        role_id = roleId;
    }

    public String getRoleName() {
        return roleName;
    }
    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleName(String n) { roleName = n; }
    public void setRoleCode(String c) { roleCode = c; }

    public static Role Create(Integer roleId) throws SQLException {

        Connection connection = SQLConnectionManager.getConnection();
        Statement statement = connection.createStatement();
        ResultSet sqlRole = statement.executeQuery("SELECT * FROM software.roles WHERE role_id = " + roleId + " LIMIT 1;");

        if(! sqlRole.next()){
            return null;
        }

        Role role = new Role(roleId);

        role.setRoleName(sqlRole.getString("role_name"));
        role.setRoleCode(sqlRole.getString("role_code"));

        return role;
    }
}
