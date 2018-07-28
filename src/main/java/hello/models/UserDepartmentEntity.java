package hello.models;

import leap.orm.annotation.Column;
import leap.orm.annotation.Id;
import leap.orm.annotation.Table;
import leap.orm.model.Model;

import java.sql.Date;


@Table(name = "USER_DEPARTMENT")
public class UserDepartmentEntity extends Model {
    @Id
    private String DId;
    @Id
    private String UId;
    @Id
    private String UdUid;
    @Column
    private Date UdTime;

}
