package org.tain.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_member")
@Data
@NoArgsConstructor
//@ToString(exclude = {})
public class Member {

	@Id
	private String uid;
	
	private String uname;
}
