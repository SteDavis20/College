;
; CSU11021 Introduction to Computing I 2019/2020
; Mode
;

	AREA	RESET, CODE, READONLY
	ENTRY

	; remember to increment in 4 instead of 1
	; store (STRW?) the result, i.e the mode, in R0
	
	LDR	R4, =tstN	; load address of tstN
	LDR	R1, [R4]	; load value of tstN; R1 = 8

	LDR	R2, =tstvals	; load address of numbers/sequence of values
	ADD	R3, R4, #4	; address of second value = address of first value + 4
	LDR	R7, [R3]	; load second value of sequence from R3 into R7
	LDR	R5, =0		; count = 0
	LDR	R8, =0		; modeCount = 0

whCount
	CMP	R5, R1		; while (count < 8) {
	BHS	endCount
	LDRB	R6, [R2]	; load first value of sequence from address R2 into R6
	CMP	R6, R7		; if (first value = second value) {
	BNE	notSameVal
	ADD	R8, R8, #1	; modeCount++
notSameVal
	ADD	R3, R3, #4	; value = nextValueInMemory
	LDR	R7, [R3]	; load nextValue from address R3 into R7
	ADD	R5, R5, #1	; count++
	
	STR	R8, [R0]	; store modeCount in R0
	B	whCount
	
endCount

STOP	B	STOP

tstN	DCD	8			; N (number of numbers)
tstvals	DCD	5, 3, 7, 5, 3, 5, 1, 9	; numbers

	END
