package com.app.patest.type.handler;

import com.app.patest.entity.User;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @ClassName: RoleEnumHandler
 * @Author: Yangyang
 * @Date: 2019/6/14
 * @Description: TODO
 */
@MappedTypes(RoleEnum.class)
@MappedJdbcTypes(JdbcType.INTEGER)
public class RoleEnumHandler extends BaseTypeHandler<RoleEnum> {
    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, RoleEnum roleEnum, JdbcType jdbcType) throws SQLException {

    }

    @Override
    public RoleEnum getNullableResult(ResultSet resultSet, String s) throws SQLException {
        int code = resultSet.getInt(s);
        if(code>=0&&code<=2){
            return RoleEnum.getInstance(code);
        }
        return null;
    }

    @Override
    public RoleEnum getNullableResult(ResultSet resultSet, int i) throws SQLException {
        int code = resultSet.getInt(i);
        if(code>=0&&code<=2){
            return RoleEnum.getInstance(code);
        }
        return null;
    }

    @Override
    public RoleEnum getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        int code = callableStatement.getInt(i);
        if(code>=0&&code<=2){
            return RoleEnum.getInstance(code);
        }
        return null;
    }
}
