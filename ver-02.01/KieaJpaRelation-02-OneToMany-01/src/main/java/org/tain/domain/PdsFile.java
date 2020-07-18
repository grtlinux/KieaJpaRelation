package org.tain.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_file")
@Data
@NoArgsConstructor
public class PdsFile {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long fid;
	
	private String fname;
}
