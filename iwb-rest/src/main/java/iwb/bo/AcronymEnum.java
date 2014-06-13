package iwb.bo;

public enum AcronymEnum {
	 OMR ("Ordure M�nag�re recyclable"),
	 OMNR ("Ordure M�nag�re non recyclable"),
	 VER ("Ver recyclable"),
	 VENR ("Ver non recyclable"),
	 PAR ("Plastique recyclable"),
	 PANR  ("Plastique non recyclable"),
	 JM  ("Journaux magazines"),
	 COMP ("Compost");
	 
	 private String description;
	 
	 private AcronymEnum(String desc){
		 this.description = desc;
	 }
	 
	 public String getDescription(){
		 return this.description;
	 }


}
