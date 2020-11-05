;
; CSU11021 Introduction to Computing I 2019/2020
; 64-bit Shift
;

	AREA	RESET, CODE, READONLY
	ENTRY

	LDR	R1, =0x12121212		; most significaint 32 bits (63 ... 32)
	LDR	R0, =0xabababab		; least significant 32 bits (31 ... 0)
	LDR	R2, =16			; shift count		test with this value?

	; if count is negative, shift left by numberOfBits = count
	; if count is positive, shift right by numberOfBit = count
	; Shift out in R0 and Shift in in R1 (or vice versa)
	; Shift one bit at a time in for loop
	; or
	; Shift entire thing in one block/move - Few instructions but one operation
	; Isolate, copy into register

	CMP	R2, #0			; if count is negative i.e count <0
	BGE	notNegative
	NEG	R2, R2 			; numberOfBitsToShift = modulus of count
	MOV	R3, R0			; copy of least significant bits
	RSB	R4, R2, #32		; contains the number of bits we don't want to shift
	MOVS	R3, R3, LSR R4		; isolate bitsToShift by making them the most least significant bits
	MOVS	R0, R0, LSL R2		; Shift to the left by numberOfBitsToShift
	MOVS	R1, R1, LSL R2		; Shift to the left by numberOfBitsToShift
	ORR	R1, R1, R3		; Add shifted bits from LSB to MSB
	B	endNow
notNegative
	CMP	R2, #0			; if count is positive i.e. count >0
	BLE	endNow
	MOV	R3, R1			; copy of most significant bits
	RSB	R4, R2, #32		; number of bits we don't want to shift 
	MOVS	R3, R3, LSL R4		; isolate bitsToShift by making them the most significant bits
	MOVS	R0, R0, LSR R2		; Shift least significant bits to right by numberOfBitsToShift
	MOVS	R1, R1, LSR R2		; Shift most significant bits to right by numberOfBitsToShift
	ORR	R0, R0, R3		; Add shifted bits from MSB to LSB
endNow
STOP	B	STOP

	END
