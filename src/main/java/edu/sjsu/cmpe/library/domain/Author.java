package edu.sjsu.cmpe.library.domain;

public class Author {
	
	private long id;
	private String name;
	/*private static int authid;
   // private final Long generateID() {
			// increment existing authID and return the new value
		return Long.valueOf(++authid);
	
   }*/
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getId() {
		return id;
	}
	public Long setId(long id) {
		return this.id = id;
	}
	
	

}
