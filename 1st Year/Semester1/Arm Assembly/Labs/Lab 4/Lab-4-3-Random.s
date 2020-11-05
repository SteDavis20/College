;
; CSU11021 Introduction to Computing I 2019/2020
; Pseudo-random number generator
;

	AREA	RESET, CODE, READONLY
	ENTRY

	

	

	; for (int i = 1; i < #ofRandomNumbersRequested; i++) {
	;	Random.generator();
	;	randomNumber = Random.Generator
	;	valueAtAddress = randomNumber;
	;	addressLocation++
	; }
	; Linear Congruential Generator
	; number = (seed*f + 1) % m
	; seed = start value
	; f = a multiplier
	; a = (seed*f + 1)
	; b = m
	
	LDR	R0, =0x40000000	; start address for pseudo-random sequence
	LDR	R1, =64		; number of pseudo-random values to generate
	LDR	R2, =0		; i = 0
	LDR	R3, =0x12345678	; seed has to be in hex value (I chose random value)
	LDR	R4, =0x38474632	; f value (I chose random value)
	LDR	R5, =0x68578298	; m = random value I made up
	LDR	R7, =0		; quotient = 0;
	LDR	R8, =0		; remainder = 0;
	LDR	R9, =0x80000000	; mask = 0x80000000;
	;MOV	R11, #23	; some random multiplier to change f
	;MOV	R12, #36	; some random multiplier to change m

	; randomNumber = (seed*f + 1) % m
for
	CMP	R2, R1		; for (i<numberOfRandomNumbersRequested;) {
	BHS	endFor
	MUL	R6, R4, R3	; randomNumber = f * seed
	ADD	R6, R6, #1	; randomNumber = randomNumber++
while
	CMP	R9, #0		; while (mask!= 0) {
	BEQ	endWhile	; end loop when condition is false
	MOV	R8, R8, LSL #1; ; remainder = remainder << 1
	
	AND	R10, R6, R9	; R10 = randomNumber & mask... 
	CMP	R10, #0		; if ( (a & mask) != 0)
	BEQ	andEqZero
	ORR	R8, R8, #1	; remainder = remainder | 1;	
	
andEqZero
	CMP	R8, R5		; if (remainder >= m) {
	BLO	shiftMask
	SUB	R8, R8, R5	; remainder = remainder - m;
	ORR	R7, R7, R9	; quotient = quotient | mask;
shiftMask
	MOV	R9, R9, LSR #1	; mask = mask >> 1;
	;MUL	R6, R8, R6	; randomNumber = (seed*f + 1) * remainder
	;MUL	R4, R11, R4
	;MUL	R5, R12, R5
	B	while		; }
endWhile
	;
	STR	R8, [R0]	; random value is stored in memory at address
	ADD	R0, R0, #4	; address+4 i.e update address
	ADD	R2, R2, #1	; i++
	B	for		; }
endFor

STOP	B	STOP

	END

; source of formula: https://en.wikipedia.org/wiki/Linear_congruential_generator
