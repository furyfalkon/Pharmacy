package gamelogic;

import items.Item;

public class Lager {
    Item[] items;
    int Amount[];
    public Lager(int Zise){
        this.items=new Item[Zise];
    }


    public Item[] getItems() {
        return items;
    }

    public void setItems(Item[] items) {
        this.items = items;
    }

    public int[] getAmounts() {
        return Amount;
    }

    public void setAmount(Lager lager ,int pos,int amount) {
        lager.Amount[pos]=amount;
    }

    public Item getItem(Lager lager ,int pos) {

        return items[pos];
    }
    /**
     * @param ziehlLager lager in welches die Items verschoben werden sollen
     * @param  sourceLager lager aus dem die Items entnommen werden
     * @param pos Position des zu verschiebenden Items im Source lager
    Items werden aus einem Source Lager in den ersten Slot eines Zieh-lagers verschoben, in welchem dieses bereits vorhanden ist,
            falls das item noch nicht vorhanden ist, wird der erste freie Slot verwendet.*/
    public void transferItem(Lager ziehlLager,Lager sourceLager,int pos) {

        for (int i = 0; i < ziehlLager.items.length; i++) {
            if (ziehlLager.items[i] == sourceLager.items[pos]) {
                ziehlLager.Amount[i] = ziehlLager.Amount[i]+sourceLager.Amount[pos];
                emtyPos(sourceLager,pos);
                return ;
            }
        }
        for (int i = 0; i < ziehlLager.items.length; i++) {
            if (ziehlLager.items[i] == null) {
                ziehlLager.items[i]=sourceLager.items[pos];
                ziehlLager.Amount[i] =sourceLager.Amount[pos];
                emtyPos(sourceLager,pos);
                return ;
            }
        }
    }
    /**
     * @param ziehlLager lager in welches die Items verschoben werden sollen
     * @param  sourceLager lager aus dem die Items entnommen werden
     * @param sourcePos Position des zu verschiebenden Items im Source lager
     * @param ziehlPos Position in die Verschoben wird
    Ein Item wird aus einem Source Lager in den ausgewählten Slot im Ziel-Lager verschoben.*/
    public void addOneItem(Lager ziehlLager,Lager sourceLager,int sourcePos,int ziehlPos) {
        if(ziehlLager.items[ziehlPos]==sourceLager.items[sourcePos]){
            if (sourceLager.Amount[sourcePos]>0) {
                ziehlLager.Amount[ziehlPos]++;
                sourceLager.Amount[ziehlPos]--;
            }
            if (sourceLager.Amount[sourcePos]==0){
            emtyPos(sourceLager,sourcePos);}
        }
        if(ziehlLager.items[ziehlPos]==null){
            if (sourceLager.Amount[sourcePos]>0) {
                ziehlLager.items[ziehlPos]=sourceLager.items[sourcePos];
                ziehlLager.Amount[ziehlPos]++;
                sourceLager.Amount[ziehlPos]--;
            }
            if (sourceLager.Amount[sourcePos]==0){
                emtyPos(sourceLager,sourcePos);}
        }


    }

    private void emtyPos(Lager lager,int pos){
        lager.Amount[pos]=0;
        lager.items[pos]=null;
    }

}




