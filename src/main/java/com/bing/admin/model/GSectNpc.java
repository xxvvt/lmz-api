package com.bing.admin.model;


public class GSectNpc {

  private long id;
  private long sectId;
  private long npcId;
  private String npcName;
  private String npcPosition;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public long getSectId() {
    return sectId;
  }

  public void setSectId(long sectId) {
    this.sectId = sectId;
  }


  public long getNpcId() {
    return npcId;
  }

  public void setNpcId(long npcId) {
    this.npcId = npcId;
  }


  public String getNpcName() {
    return npcName;
  }

  public void setNpcName(String npcName) {
    this.npcName = npcName;
  }


  public String getNpcPosition() {
    return npcPosition;
  }

  public void setNpcPosition(String npcPosition) {
    this.npcPosition = npcPosition;
  }

}
