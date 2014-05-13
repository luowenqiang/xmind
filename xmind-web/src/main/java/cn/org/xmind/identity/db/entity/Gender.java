package cn.org.xmind.identity.db.entity;

/**
 *
 * @author rodney
 */
public enum Gender {

    /**
     * 男性
     */
    MALE("MALE"),
    /**
     * 女性
     */
    FEMALE("FEMALE");
    private String value;

    private Gender(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }
}
