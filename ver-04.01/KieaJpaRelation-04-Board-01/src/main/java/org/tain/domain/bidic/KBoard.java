package org.tain.domain.bidic;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "tb_kboard")
@Data
@NoArgsConstructor
@ToString(exclude = {"kreplies"})
public class KBoard {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String title;
	
	private String writer;
	
	private String content;
	
	@CreationTimestamp
	private Timestamp createDate;
	
	@UpdateTimestamp
	private Timestamp updateDate;
	
	//@OneToMany(mappedBy = "board")
	@OneToMany(mappedBy = "kboard", targetEntity = KReply.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	//@OneToMany(mappedBy = "board", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<KReply> kreplies;
}
