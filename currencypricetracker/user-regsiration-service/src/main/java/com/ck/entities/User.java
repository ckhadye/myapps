package com.ck.entities;

import com.ck.entities.Currency;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.springframework.web.jsf.FacesContextUtils;

@Entity
@Table(name = "USER")
public class User {

	@Id()
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "user_name", nullable = false, length = 16)
	private String userName;
	
	@Column(name = "password", nullable = false, length = 16)
	private String password;
	
	@Column(name = "email", nullable = false)
	private String email;
	
//	@OneToMany()
	@ManyToMany
	@JoinTable(name = "USER_CURRENCIES", joinColumns = {
//			@JoinColumn(name = "USER_ID", referencedColumnName = "id" , updatable=false,nullable=false,unique=false) }, inverseJoinColumns = {
					@JoinColumn(name = "USER_ID") }, inverseJoinColumns = {
					@JoinColumn(name = "CURRENCY_ID") }, uniqueConstraints=@UniqueConstraint(columnNames= {}))
	private Set<Currency> currencies;
	
	public User() {
		super();
	}
	
	public User(String userName,String password, String email, Set<Currency> currencies) {
		this.userName = userName;
		this.password = password;
		this.email = email;
		this.currencies = currencies;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Currency> getCurrencies() {
		return currencies;
	}

	public void setCurrencies(Set<Currency> currencies) {
		this.currencies = currencies;
	}

	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
		
	}
	


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((currencies == null) ? 0 : currencies.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((userName == null) ? 0 : userName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (currencies == null) {
			if (other.currencies != null)
				return false;
		} else if (!currencies.equals(other.currencies))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (userName == null) {
			if (other.userName != null)
				return false;
		} else if (!userName.equals(other.userName))
			return false;
		return true;
	}


}
