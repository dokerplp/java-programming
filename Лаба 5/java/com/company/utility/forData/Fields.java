package com.company.utility.forData;

import com.company.data.Organization;
import com.company.data.Product;
import com.company.exceptions.InfinityRecursionException;

import java.time.ZonedDateTime;

public class Fields {

    /**
     * Last product id its getter, setter and method making new id
     */
    private int ProdLastId = 649432;
    public int getProdLastId() {
        return ProdLastId;
    }
    public void setProdLastId(int prodLastId) {
        ProdLastId = prodLastId;
    }
    public void NewID(Product product){
        ProdLastId++;
        product.setId(ProdLastId);
    }

    /**
     * Last organization id its getter, setter and method making new id
     */
    private int OrgLastId = 563592;
    public int getOrgLastId() {
        return OrgLastId;
    }
    public void setOrgLastId(int orgLastId) {
        OrgLastId = orgLastId;
    }
    public void NewID(Organization organization){
        OrgLastId++;
        organization.setId(OrgLastId);
    }

    /**
     * Last Initialization time, its getter, setter
     */
    private ZonedDateTime LastInit = null;
    public String getLastInit() {
        if (LastInit == null) return "Еще не было инициализации";
        else return LastInit.toString();
    }
    public void setLastInit() {
        LastInit = ZonedDateTime.now();
    }

    /**
     * Last Save time, its getter, setter
     */
    private ZonedDateTime LastSave = null;
    public String getLastSave() {
        if (LastSave == null) return "Еще не было сохранения";
        else return LastSave.toString();
    }
    public void setLastSave() {
        LastSave = ZonedDateTime.now();
    }

    /**
     * Counts amount of recursions
     */
    private int RecursionCounter = 0;

    /**
     * Increments counter
     */
    public void RecPlus(){
        RecursionCounter++;
    }

    /**
     * Makes counter equal 0
     */
    public void Putin(){
        RecursionCounter = 0;
    }

    /**
     * Checking amount of recursions
     * @throws InfinityRecursionException if it need
     */
    public  void newRec() throws InfinityRecursionException{
        if (RecursionCounter >= 100) throw new InfinityRecursionException();
        else RecPlus();
    }




}
