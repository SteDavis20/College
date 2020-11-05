;
; CSU11021 Introduction to Computing I 2019/2020
; Bit Manipulation
;

	AREA	RESET, CODE, READONLY
	ENTRY

	LDR	R0, =137	; a = 137
	LDR	R1, =6		; b = 6

	; This program implements unsigned division
	; Verify your solution by testing with different values for a and b
	
	LDR	R2, =0		; quotient = 0;
	LDR	R3, =0		; remainder = 0;
	LDR	R4, =0x80000000	; mask = 0x80000000;
	;LDR	R5, [R4]
while
	CMP	R4, #0		; while (mask!= 0) {
	BEQ	endWhile	; end loop when condition is false
	MOV	R3, R3, LSL #1; ; remainder = remainder << 1
	
	AND	R6, R0, R4	; R0 = a & mask... is this instructon for 1 bit? Because & is singular not double (&&)
	CMP	R6, #0		; if ( (a & mask) != 0)
	BEQ	andEqZero
	ORR	R3, R3, #1	; remainder = remainder | 1;	
	
andEqZero
	CMP	R3, R1		; if (remainder >= b) {
	BLO	shiftMask
	SUB	R3, R3, R1	; remainder = remainder - b;
	ORR	R2, R2, R4	; quotient = quotient | mask;
shiftMask
	MOV	R4, R4, LSR #1	; mask = mask >> 1;
	B	while		; }
endWhile		
STOP	B	STOP

	END
