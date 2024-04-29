package dw.jdbcproject.repository;

import dw.jdbcproject.model.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class JdbcMemberRepository implements MemberRepository{
    @Autowired
    DataSource dataSource;
    @Override
    public Member save(Member member) {
        String sql = "insert into members(name) values(?)";
        //sql 패키지 내의 클래스 정의
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = DataSourceUtils.getConnection(dataSource);
            pstmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, member.getName());
            pstmt.executeUpdate();
            rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                member.setId(rs.getLong(1));
            } else {
                throw new SQLException("ID 조회 실패");
            }
            return member;

        }catch (Exception e) {
            // 예외처리
            throw new IllegalStateException(e);
        } finally {
            // 연결종료
            close(conn, pstmt, rs);
        }
    }

    @Override
    public Optional<Member> findById(long id) {
        return Optional.empty();
    }

    @Override
    public Optional<Member> findByName(String name) {
        return Optional.empty();
    }

    @Override
    public List<Member> findAll() {
        String sql = "select * from members";
        //sql 패키지 내의 클래스 정의
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = DataSourceUtils.getConnection(dataSource);
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            List<Member> members = new ArrayList<>();
            while (rs.next()) {
                Member member =  new Member();
                member.setId(rs.getLong(1));
                member.setName(rs.getString(2));
                members.add(member);
            }
            return members;

        }catch (Exception e) {
            // 예외처리
            throw new IllegalStateException(e);
        } finally {
            // 연결종료
            close(conn, pstmt, rs);
        }
    }

    private void close(Connection conn, PreparedStatement pstmt, ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            if (pstmt != null) {
                pstmt.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}



