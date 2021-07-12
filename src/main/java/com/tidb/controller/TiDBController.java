package com.tidb.controller;

import com.tidb.model.Variable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@RestController
public class TiDBController {

    @Resource
    private JdbcTemplate jdbcTemplate;

    @RequestMapping("/getVariable")
    public List<Variable> getVariable() {
        String sql = "select * from tidb";

        List<Variable> variables = jdbcTemplate.query(sql, new RowMapper<Variable>() {
            @Override
            public Variable mapRow(ResultSet resultSet, int i) throws SQLException {
                Variable variable = new Variable();
                variable.setVariableName(resultSet.getString("VARIABLE_NAME"));
                variable.setVariableValue(resultSet.getString("VARIABLE_VALUE"));
                variable.setComment(resultSet.getString("COMMENT"));
                return variable;
            }
        });
        return variables;
    }
}
