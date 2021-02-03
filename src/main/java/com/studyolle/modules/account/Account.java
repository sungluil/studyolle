package com.studyolle.modules.account;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;

import org.springframework.transaction.annotation.Transactional;

import com.studyolle.modules.study.Study;
import com.studyolle.modules.tag.Tag;
import com.studyolle.modules.zone.Zone;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @EqualsAndHashCode(of = "id")
@Builder @AllArgsConstructor @NoArgsConstructor
public class Account {

	@Id @GeneratedValue
	private Long id;
	
	@Column(unique = true, nullable = false)
	private String email;
	
	@Column(unique = true, nullable = false)
	private String nickname;
	
	private String password;
	
	private boolean emailVerified;
	
	private String emailCheckToken;
	
	private LocalDateTime emailCheckTokenGeneratedAt;
	
	private LocalDateTime joinedAt;
	
	private String bio;
	
	private String url;
	
	private String occupation;
	
	private String location;
	
	@Lob @Basic(fetch = FetchType.EAGER)
	private String profileImage;
	
	private boolean studyCreatedByEmail;
	
	private boolean studyCreatedByWeb = true;
	
	private boolean studyEnrollmentResultByEmail;
	
	private boolean studyEnrollmentResultByWeb = true;
	
	private boolean studyUpdatedByEmail;
	
	private boolean studyUpdatedByWeb = true;
	
	//List보다 Set을 선호
	@ManyToMany
	private Set<Tag> tags = new HashSet<>();
	
	@ManyToMany
	private Set<Zone> zones = new HashSet<>();
	
	public void generateEmailCheckToken() {
		this.emailCheckToken = UUID.randomUUID().toString();
		this.emailCheckTokenGeneratedAt = LocalDateTime.now();
	}

	public void completeSignUp() {
		this.emailVerified = true;
		this.joinedAt = LocalDateTime.now();
	}

	public boolean isValidToken(String token) {
		// TODO Auto-generated method stub
		return this.emailCheckToken.equals(token);
	}

	public boolean canSendConfirmEmail() {
		// TODO Auto-generated method stub
		return this.emailCheckTokenGeneratedAt.isBefore(LocalDateTime.now().minusHours(1));
	}
	
	
}
