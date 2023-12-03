package com.example.Tunishop.Entity;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class VerificationTokenLivreur {
	
	//expiration time 10min
		private static final int EXPIRATION_TIME=10;
		
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		
		private Long id;
		
		private String token;
		private Date expirationTime;
		
		@OneToOne(fetch = FetchType.EAGER)
		@JoinColumn(name= "livreur_id",
		nullable=false,
		foreignKey=@ForeignKey(name="FK_LIVREUR_VERIFY_TOKEN"))
		private Livreur liv;
		
		public VerificationTokenLivreur() {};
		
		public VerificationTokenLivreur(Livreur user,String token) {
			super();
			this.token=token;
			this.liv=user;
			this.expirationTime=calculateExpirationTime(EXPIRATION_TIME);
		}

		

		public Date calculateExpirationTime(int expirationTime) {
			Calendar calendar=Calendar.getInstance();
			calendar.setTimeInMillis(new Date().getTime());
			calendar.add(Calendar.MINUTE, expirationTime);
			return new Date(calendar.getTime().getTime());
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getToken() {
			return token;
		}

		public void setToken(String token) {
			this.token = token;
		}

		public Date getExpirationTime() {
			return expirationTime;
		}

		public void setExpirationTime(Date expirationTime) {
			this.expirationTime = expirationTime;
		}

		public Livreur getLiv() {
			return liv;
		}

		public void setLiv(Livreur liv) {
			this.liv = liv;
		}
		
		
		

}
