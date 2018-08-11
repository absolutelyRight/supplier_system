package hello.models;

import leap.orm.annotation.Id;
import leap.orm.annotation.Table;
import leap.orm.model.Model;

@Table(name = "ArchiveAffix", autoCreate = true)
public class ArchiveAffixEntity extends Model {
    //投标附件关联表
    @Id
    private String AffixId;
    @Id
    private String Archive;

    public String getAffixId() {
        return AffixId;
    }

    public void setAffixId(String affixId) {
        AffixId = affixId;
    }

    public String getArchive() {
        return Archive;
    }

    public void setArchive(String archive) {
        Archive = archive;
    }
}
