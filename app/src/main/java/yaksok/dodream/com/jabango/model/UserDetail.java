package yaksok.dodream.com.jabango.model;

public class UserDetail {
   private String st_name,st_number,st_major,st_major_chairman,st_bank,st_account,st_parent_name_1,st_parent_job_1,
    st_parent_name_2,st_parent_job_2,st_unable_check,st_unable_name,st_unable_rank;
    private boolean unable;
    private boolean able;
    int major_spin,bank_spin,spin_reli_1,spin_reli_2,spin_apply,spin_dormi;
    String sn_major;

    public String getSt_major() {
        return st_major;
    }

    public void setSt_major(String st_major) {
        this.st_major = st_major;
    }

    public boolean isAble() {
        return able;
    }

    public void setAble(boolean able) {
        this.able = able;
    }



    public boolean isUnable() {
        return unable;
    }

    public void setUnable(boolean unable) {
        this.unable = unable;
    }

    public String getSt_name() {
        return st_name;
    }

    public void setSt_name(String st_name) {
        this.st_name = st_name;
    }

    public String getSt_number() {
        return st_number;
    }

    public void setSt_number(String st_number) {
        this.st_number = st_number;
    }



    public String getSt_major_chairman() {
        return st_major_chairman;
    }

    public void setSt_major_chairman(String st_major_chairman) {
        this.st_major_chairman = st_major_chairman;
    }


    public String getSt_account() {
        return st_account;
    }

    public void setSt_account(String st_account) {
        this.st_account = st_account;
    }

    public String getSt_parent_name_1() {
        return st_parent_name_1;
    }

    public void setSt_parent_name_1(String st_parent_name_1) {
        this.st_parent_name_1 = st_parent_name_1;
    }



    public String getSt_parent_job_1() {
        return st_parent_job_1;
    }

    public void setSt_parent_job_1(String st_parent_job_1) {
        this.st_parent_job_1 = st_parent_job_1;
    }

    public String getSt_parent_name_2() {
        return st_parent_name_2;
    }

    public void setSt_parent_name_2(String st_parent_name_2) {
        this.st_parent_name_2 = st_parent_name_2;
    }



    public String getSt_parent_job_2() {
        return st_parent_job_2;
    }

    public void setSt_parent_job_2(String st_parent_job_2) {
        this.st_parent_job_2 = st_parent_job_2;
    }


    public int getMajor_spin() {
        return major_spin;
    }

    public void setMajor_spin(int major_spin) {
        this.major_spin = major_spin;
    }

    public int getBank_spin() {
        return bank_spin;
    }

    public void setBank_spin(int bank_spin) {
        this.bank_spin = bank_spin;
    }

    public int getSpin_reli_1() {
        return spin_reli_1;
    }

    public void setSpin_reli_1(int spin_reli_1) {
        this.spin_reli_1 = spin_reli_1;
    }

    public int getSpin_reli_2() {
        return spin_reli_2;
    }

    public void setSpin_reli_2(int spin_reli_2) {
        this.spin_reli_2 = spin_reli_2;
    }

    public int getSpin_apply() {
        return spin_apply;
    }

    public void setSpin_apply(int spin_apply) {
        this.spin_apply = spin_apply;
    }

    public int getSpin_dormi() {
        return spin_dormi;
    }

    public void setSpin_dormi(int spin_dormi) {
        this.spin_dormi = spin_dormi;
    }
}
