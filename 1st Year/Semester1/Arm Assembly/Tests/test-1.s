;
; CSU11021 Introduction to Computing I 2019/2020
; e-Test Question 1
;

	AREA	RESET, CODE, READONLY
	ENTRY

	MOV	R5, #3
	MOV	R1, #3
	MUL	R3, R1, R1
	MUL	R2, R3, R1
	MUL	R2, R5, R2
	MOV	R4, #4
	MUL	R3, R4, R3
	ADD	R0, R2, R3
	ADD	R0, R0, #6

	; R0 = 0x0000007B	;result when R1 is initialised to 0x00000003

STOP	B	STOP

	END
