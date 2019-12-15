package com.example.courseJPA.entities.enums;

public enum OrderStatus {

	WAITING_PAYMENT(0), PAID(1), SHIPPED(2), DELIVERED(3), CANCELED(4);

	private int codOrderEnum;

	private OrderStatus(int codOrderEnum) {
		this.codOrderEnum = codOrderEnum;
	}

	/*
	 * Agora fazer um método para pegar algum código que o usuário digite e iremos
	 * trazer o texto que está anexado ao índice acima de cada status
	 */
	
	public int getCodeEnum() {
		return codOrderEnum;
	}
	
	public static OrderStatus valueOf(int codEnum) {
		for (OrderStatus cod : OrderStatus.values()) {
			if(cod.getCodeEnum() == codEnum) {
				return cod;
			}
		}
		throw new IllegalArgumentException("Codigo de status informado é inexistente");
	}

}
