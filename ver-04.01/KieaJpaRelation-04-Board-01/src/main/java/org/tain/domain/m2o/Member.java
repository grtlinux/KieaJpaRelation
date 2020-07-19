package org.tain.domain.m2o;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_member")
@Data
@NoArgsConstructor
public class Member {

	@Id
	private String uid;
	
	private String uname;
}
