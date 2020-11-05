;
; CS1022 Introduction to Computing II 2018/2019
; Lab 3 - Floating-Point
;

	AREA	RESET, CODE, READONLY
	ENTRY

;
; Test Data
;
FP_A	EQU	0xBF400000		; -0.75
FP_B	EQU	0x3FE00000		; 1.75
; Expected result = 1, i.e. R2 = 0x3F800000

	; initialize system stack pointer (SP)
	LDR	SP, =0x40010000
	
	LDR	R0, =FP_A		; fpNumber1
	LDR	R1, =FP_B		; fpNumber2
	
	BL	fpadd
	
stop	B	stop

; --------------------------------------------------------------------------------------------------------------------
; fpsign
; decodes an IEEE 754 floating point value to return the sign bit (bit 31)
; parameters:
;	r0 - ieee 754 float
; return:
;	r1 - sign (bit 31)
;
fpsign
	PUSH	{R12}
	MOV	R12, R0			; fp number
	AND	R12, R12, #0x80000000	; AND with mask to isolate bit 31
	MOV	R12, R12, LSR #31	; get sign bit value

	MOV	R1, R12			; sign value
	POP	{R12}
	BX	LR

; ----------------------------------------------------------------------------------------------------------------------
; fpexp
; decodes an IEEE 754 floating point value to the signed (2's complement)
; exponent
; parameters:
;	r0 - ieee 754 float
; return:
;	r2 - exponent (signed 2's complement word)
;
fpexp		
	PUSH	{R10-R12}
	MOV	R10, R0			; fp number
	LDR	R11, =0x7F800000	; mask to clear all bits except exponent
	
	AND	R12, R10, R11		; clear all bits except exponent 
	MOV	R12, R12, LSR #23	; isolate exponent
	SUB	R12, R12, #127		; exponent -= 127
	MOV	R2, R12			; return exponent in R0
	POP	{R10-R12}
	BX	LR

; -------------------------------------------------------------------------------------------------------------------------
; fpfrac
; decodes an IEEE 754 floating point value to the signed (2's complement)
; fraction
; parameters:
;	r0 - ieee 754 float
; return:
;	r3 - fraction (signed 2's complement word)
;
fpfrac	
	PUSH	{R10-R12}
	MOV	R10, R0			; fp number copy
	LDR	R11, =0x007FFFFF	; mask to isolate fraction
	AND	R10, R10, R11		; isolate fraction
	MOV	R3, R10			; return fraction in R0
	POP	{R10-R12}
	BX	LR
	
; ----------------------------------------------------------------------------------------------------------------------
; fpencode
; encodes an IEEE 754 value using a specified fraction and exponent
; parameters:
;	r0 - fraction (signed 2's complement word)
;	r1 - exponent (signed 2's complement word)
; result:
;	r2 - resulting ieee 754 float from addition
;
fpencode
	PUSH	{R10-R12}
	MOV	R10, R0			; fraction
	MOV	R11, R1			; exponent
	
	ADD	R11, R11, #127		; exponent += 127
	MOV	R11, R11, LSL #23	; return exponent to original bit positions
	ORR	R2, R11, R10		; sexponentfractionfractionfractio - each letter represents a bit
	POP	{R10-R12}
	BX	LR
; ------------------------------------------------------------------------------------------------------------------------
; fpadd
; Adds two IEEE 754 floating point values and returns the result
; parameters:
;	r0 - float number 1
;	r1 - float number 2
; return:
;	r2 - result float
;
fpadd
	PUSH	{R4-R12}
	MOV	R8, R1			; fpNumber2Copy
	
	; first decode fpNumber1
	BL	fpsign			; fpsign(fpNumber1);
	MOV 	R4, R1			; R4 = signN1
	BL	fpexp			; fpexp(fpNumber1);
	MOV	R5, R2			; R5 = exponentN1
	BL	fpfrac			; fpfrac(fpNumber1)
	MOV	R6, R3			; R6 = fractionN1
	
	; second decode fpNumber2
	MOV	R0, R8			; R0 = fpNumber2
	BL	fpsign			; fpsign(fpNumber2);
	MOV 	R7, R1			; R7 = signN2
	BL	fpexp			; fpexp(fpNumber2);
	MOV	R8, R2			; R8 = exponentN2
	BL	fpfrac			; fpfrac(fpNumber2)
	MOV	R9, R3			; R9 = fractionN2
	
	; remember to introduce the hidden bit of each fraction (bit 23)!
	ORR	R6, R6, #0x00800000 	; 1.fractionN1	(introduce hidden bit)
	ORR	R9, R9, #0x00800000	; 1.fractionN2	(introduce hidden bit)
	
	; exponents must be == before we can proceed, hence check, and if !=, adjust so they are ==.
	; fraction associated with the smaller exponent is shifted accordingly
	ADD	R5, R5, #127		; exponentN1+=127 to deal with possible negative exponents
	ADD	R8, R8, #127		; exponentN2+=127 to deal with possible negative exponents
	CMP	R5, R8			; if (exponentN1<exponentN2) 
	BHS	firstExpBigger
	; if code continues normally here then exponentN1 < exponentN2
secondExpBigger
	SUB	R10, R8, R5		; expDifference = exponentN2 - exponentN1
	ADD	R5, R5, R10		; exponentN1 += difference (now exponentN1 == exponentN2)
	MOV	R6, R6, LSR R10		; adjust fraction to compensate for increased exponent
	B	exponentsEqual
firstExpBigger
	CMP	R5, R8			; if (exponentN1>exponentN2)
	BEQ	exponentsEqual
	SUB	R10, R5, R8		; expDifference = exponentN1 - exponentN2
	ADD	R8, R8, R10		; exponent2 += expDifference (now exponentN1 == exponentN2)
	MOV	R9, R9, LSR R10		; move radix point of fraction to to compensate for increased exponent
	B	exponentsEqual
exponentsEqual
	SUB	R5, R5, #127		; undo earlier addition
	SUB	R8, R8, #127		; undo earlier addition
	MOV	R2, R5			; R2 = exponent of result excluding bias
	
	; now we can add (if numbers are positive) because exponents are equal
	; check if numbers are positive or negative, if negative, convert fraction to 2's complement value.
	LDR	R12, =0xFFFFFFFF	; mask to invert bits
	CMP	R4, #0			; (if signN1 == 0)
	BNE	number1Negative
	CMP	R7, #0			; (if signN2 == 0)
	BNE	number2Negative
	B	canAdd
number1Negative
	EOR	R6, R6, R12		; invert bits of fractionN1
	ADD	R6, R6, #1		; gives 2's Compliment value of fractionN1
	CMP	R7, #1			; if (signN2==0)
	BNE	canAdd
number2Negative
	EOR	R9, R9, R12		; invert bits of fractionN2
	ADD	R9, R9, #1		; gives 2's Compliment value of fractionN2
canAdd
	ADD	R3, R6, R9		; resultFraction = fractionN1 + fractionN2
	; need to normalise resultFraction (may already be normalised, check!).
higherThan2
	CMP	R3, #0x01000000		; if (resultFraction >= 2)
	BLO	lowerThan2
	MOV	R3, R3, LSR #1		; shift radix point to the left
	ADD	R2, R2, #1		; resultExponent++
	B	higherThan2		
lowerThan2
	CMP	R3, #0x00800000		; if (resultFraction >= 1)
	BLO	lowerThan1	
	B	isNormalised
lowerThan1
	MOV	R3, R3, LSL #1		; shift radix point to the right
	SUB	R2, R2, #1		; resultExponent--
	B	lowerThan2
isNormalised
; If resultFraction is negative, need to convert back into positive form but set the sign bit of the result = -1. 
	CMP	R3, #0			; if (resultFraction < 0)
	BHS	encode
	EOR	R3, R3, R12		; invert bits of resultFraction
	ADD	R3, R3, #1		; these 2 steps converts resultFraction back into positive form
	LDR	R1, =0x80000000		; mask for negative sign bit
	ORR	R3, R3, R1		; 1________resultFractionnnnnnnnnn
encode
	; don't forget to remove hidden bit!
	EOR	R3, R3, #0x00800000	; remove hidden bit
	MOV	R0, R3			; pass parameters into correct registers
	MOV	R1, R2			; pass parameters into correct registers
	BL	fpencode

	; return result in R2
	POP	{R4-R12}
	BX	LR
;----------------------------------------------------------------------------------------------------------------------
	END
		
		
;------------------------------------------------------------------------------------------------	
		; R0 = fp number
		;
		; R1 = sign bit
		; R2 = exponent
		; R3 = fraction
		;
		; R4 = signN1
		; R5 = exponentN1
		; R6 = fractionN1
		;
		; R7 = signN2 
		; R8 = exponentN2
		; R9 = fractionN2
		; 
		; R10
		;	
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
