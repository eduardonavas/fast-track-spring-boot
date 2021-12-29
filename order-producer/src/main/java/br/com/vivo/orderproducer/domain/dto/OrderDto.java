package br.com.vivo.orderproducer.domain.dto;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.vivo.orderproducer.domain.StatusEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDto {
	
	private Long id;
	@NotNull(message = "the name field cannot be null")
	@NotBlank(message = "the name field cannot be empty")
	private String name;
	@NotNull(message = "the description field cannot be null")
	@NotBlank(message = "the description field cannot be empty")
	private String description;
	@NotNull(message = "the total field cannot be null")
	private Double total;
	@NotNull(message = "the status field cannot be null")
	@Valid
	private StatusEnum status;
}
