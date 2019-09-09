package com.megatravel.korisniciservice.dto;

public class RegistracijaDTO {
	
    private Long id;
    private String firstName;
	private String lastName;
	private String password;
    private String repeatPassword;
    private String workCertificateNumber;
    private String email;
    private AdresaDTO adresaDTO;
    
    public RegistracijaDTO() {
    	
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRepeatPassword() {
		return repeatPassword;
	}

	public void setRepeatPassword(String repeatPassword) {
		this.repeatPassword = repeatPassword;
	}

	public String getWorkCertificateNumber() {
		return workCertificateNumber;
	}

	public void setWorkCertificateNumber(String workCertificateNumber) {
		this.workCertificateNumber = workCertificateNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public AdresaDTO getAdresaDTO() {
		return adresaDTO;
	}

	public void setAdresaDTO(AdresaDTO adresaDTO) {
		this.adresaDTO = adresaDTO;
	}
    
    
}
