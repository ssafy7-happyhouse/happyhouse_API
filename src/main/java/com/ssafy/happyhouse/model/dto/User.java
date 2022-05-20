/**
 * 
 */
package com.ssafy.happyhouse.model.dto;

/**
 * @author S20144775
 *
 */
public class User {
	private String id;
	private String password;
	private String name;
	private String address;
	private String phone;
	private String favorite;
		
	
	public User() {
	}
	
	public User(String id, String password) {
		this.id = id;
		this.password = password;
	}

	/**
	 * @param id
	 * @param name
	 * @param address
	 * @param phone
	 * @param favorite
	 */
	public User(String id, String name, String address, String phone, String favorite) {
		this.id = id;
		this.name = name;
		this.address = address;
		this.phone = phone;
		this.favorite = favorite;
	}

	/**
	 * @param id
	 * @param name
	 * @param password
	 * @param address
	 * @param phone
	 * @param favorite
	 */
	public User(String id, String password, String name, String address, String phone, String favorite) {
		this.id = id;
		this.password = password;
		this.name = name;
		this.address = address;
		this.phone = phone;
		this.favorite = favorite;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}


	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * @return the favorite
	 */
	public String getFavorite() {
		return favorite;
	}

	/**
	 * @param favorite the favorite to set
	 */
	public void setFavorite(String favorite) {
		this.favorite = favorite;
	}

	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(id);
		builder.append("\t| ");
		builder.append(name);
		builder.append("\t| ");
		builder.append(address);
		builder.append("\t| ");
		builder.append(phone);
		builder.append("\t| ");
		builder.append(favorite);
		return builder.toString();
	}

	
}
