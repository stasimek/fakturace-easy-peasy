package cz.stasimek.fakturaceeasypeasy.entity;

import cz.stasimek.fakturaceeasypeasy.enumeration.Provider;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

@Entity
@Table(name="`user`")
@Getter
@Setter
@Validated
public class User extends BaseEntity {

	@Column(length = 20)
	@Enumerated(EnumType.STRING)
	private Provider provider;

	@NotNull
	@NotBlank
	@Size(max = 255)
	@Column(length = 255, nullable = false)
	private String login;

	@Size(max = 255)
	@Column(length = 255)
	private String email;

	@Size(max = 255)
	@Column(length = 255)
	private String name;
}
