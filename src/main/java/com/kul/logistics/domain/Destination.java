package com.kul.logistics.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import lombok.Data;

/**
 * @author Shane Paulus
 * <p>
 * Date Created: 12-Sep-2022
 */

@Data
@Entity
public class Destination {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(nullable = false)
	private String name;
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "primary_destination_id")
	private List<DestinationLink> destinationLinkList;

}