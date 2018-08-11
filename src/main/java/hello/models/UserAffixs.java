package hello.models;

import leap.orm.annotation.Id;
import leap.orm.annotation.Table;
import leap.orm.model.Model;

@Table(name="UserAffixs",autoCreate=true)
public class UserAffixs extends Model {
    @Id
    private String UserId;
    @Id
    private  String AffixId;

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public String getAffixId() {
        return AffixId;
    }

    public void setAffixId(String affixId) {
        AffixId = affixId;
    }
}
