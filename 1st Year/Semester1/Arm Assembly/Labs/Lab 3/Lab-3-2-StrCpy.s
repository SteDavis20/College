;
; CSU11021 Introduction to Computing I 2019/2020
; String Copy
;

	AREA	RESET, CODE, READONLY
	ENTRY

	LDR	R0, =tststr	; load address of existing string
	LDR	R1, =0x40000000	; load address for new string
       	
;	while (chStr1 != 0) {
;		chStr1 = chStr2;
;		locationOfStr1++;
;		locationOfStr2++;
;	}

while1	
	LDRB	R2, [R0]	; load value at existing string address 
	
	CMP	R2, #0		; while (value of character in existing string != 0) {
	BEQ	endwhl		; end loop if condition is false
	
	STRB	R2, [R1]	; store ascii value at R2 into R1 (address of new string)
	ADD	R0, R0, #1	; address = address + 4
	ADD	R1, R1, #1	; address = address + 4
	
	B	while1		; repeat iteration
endwhl				; }
STOP	B	STOP

tststr	DCB	"This is a test!",0

	END
