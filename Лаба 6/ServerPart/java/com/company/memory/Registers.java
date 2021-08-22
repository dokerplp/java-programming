package com.company.memory;


import java.time.ZonedDateTime;

public class Registers {

    /**
     * Last product id its getter, setter and method making new id
     */
    private int ProdLastId = 649432;
    /**
     * Last organization id its getter, setter and method making new id
     */
    private int OrgLastId = 563592;
    /**
     * Last Initialization time, its getter, setter
     */
    private ZonedDateTime LastInit = null;
    /**
     * True - program can be saved, false - not
     */
    private boolean savesAllowed = false;
    /**
     * Last save time
     */
    private ZonedDateTime LastSave = null;

    public int getProdLastId() {
        return ProdLastId;
    }

    public void setProdLastId(int prodLastId) {
        ProdLastId = prodLastId;
    }

    public int ProdNewID() {
        return ProdLastId++;
    }

    public int getOrgLastId() {
        return OrgLastId;
    }

    public void setOrgLastId(int orgLastId) {
        OrgLastId = orgLastId;
    }

    public int OrgNewID() {
        return OrgLastId++;
    }

    public String getLastInit() {
        if (LastInit == null) return "Еще не было инициализации";
        else return LastInit.toString();
    }

    public void setLastInit() {
        LastInit = ZonedDateTime.now();
    }

    public boolean isSavesAllowed() {
        return savesAllowed;
    }

    public void setSavesAllowed(boolean savesAllowed) {
        this.savesAllowed = savesAllowed;
    }

    public String getLastSave() {
        if (LastSave == null) return "Еще не было сохранения";
        else return LastSave.toString();
    }

    public void setLastSave() {
        LastSave = ZonedDateTime.now();
    }
}
