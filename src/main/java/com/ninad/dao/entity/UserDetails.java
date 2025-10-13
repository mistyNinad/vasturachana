package com.ninad.dao.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "user_details")
public class UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    // Foreign key to User entity
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

   

    @Column(name = "email", length = 45)
    private String email;

    @Column(name = "whatsapp_number", precision = 12, scale = 0)
    private java.math.BigDecimal whatsappNumber;

  

    @Column(name = "notes", length = 100)
    private String notes;

    @Column(name = "address", length = 200)
    private String address;

    // --- Getters & Setters ---

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }

 
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public java.math.BigDecimal getWhatsappNumber() {
        return whatsappNumber;
    }
    public void setWhatsappNumber(java.math.BigDecimal whatsappNumber) {
        this.whatsappNumber = whatsappNumber;
    }

 
	public String getNotes() {
        return notes;
    }
    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
}

