package workshop.repository;

import lombok.extern.slf4j.Slf4j;
import workshop.model.Employee;
import workshop.util.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class EmployeeRepository {


    public static List<Employee> getAllEmployees() {
        log.info("getAllEmployees() method called");

        List<Employee> result = new ArrayList<>();

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = JDBCUtil.getConnection();
            statement = connection.prepareStatement("SELECT * FROM EMPLOYEE");
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Employee emp = new Employee();

                emp.setId(resultSet.getString("EMPNO"));
                emp.setName(resultSet.getString("ENAME"));
                emp.setJob(resultSet.getString("JOB"));
                emp.setMgrId(resultSet.getInt("MGR"));
                emp.setHiredate(resultSet.getDate("HIREDATE").toLocalDate());
                emp.setSalary(resultSet.getDouble("SAL"));
                emp.setCommission(resultSet.getDouble("COMM"));
                emp.setDeptNo(resultSet.getInt("DEPTNO"));

                result.add(emp);
            }
        } catch (SQLException e) {
            log.error("ERROR: ", e);
        } finally {
            JDBCUtil.close(connection, statement, resultSet);
        }
        log.info("size of employee list: {}", result.size());
        return result;
    }
}
