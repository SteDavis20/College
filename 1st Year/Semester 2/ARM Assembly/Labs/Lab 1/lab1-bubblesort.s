;
; CS1022 Introduction to Computing II 2019/2020
; Lab 1B - Bubble Sort
;

N	EQU	10

	AREA	globals, DATA, READWRITE

; N word-size values

SORTED	SPACE	N*4		; N words (4 bytes each)


	AREA	RESET, CODE, READONLY
	ENTRY


	;
	; copy the test data into RAM
	;

	LDR	R4, =SORTED
	LDR	R5, =UNSORT
	LDR	R6, =0
whInit	CMP	R6, #N
	BHS	eWhInit
	LDR	R7, [R5, R6, LSL #2]
	STR	R7, [R4, R6, LSL #2]
	ADD	R6, R6, #1
	B	whInit
eWhInit

	LDR	R4, =SORTED
	LDR	R5, =UNSORT

doWhile
	LDR	R10, =0			; swapped = false
	 LDR	R1, =1			; i = 1;
	 CMP	R1, #N
	 BHS	STOP
	 

forTwo		
	CMP	R1, #N			; (for i=1; i<N; i++) 	
	BHS	endForTwo
	SUB	R2, R1, #1		; i-1
	LDR	R3, [R4, R2, LSL #2]	; array[i-1]
	LDR	R8, [R4, R1, LSL #2]	; array[i]
	CMP	R3, R8			; if(array[i-1]>array[i]) {
	BLS	elseIf
	MOV	R9, R8			; tmpswap = array[i]	
	STR	R3, [R4, R1, LSL #2]	; array[i-1]=array[i]
	STR	R9, [R4, R2, LSL #2]	; array[i]=tmpswap
	LDR	R10, =1			; swapped = true	
elseIf
	ADD	R1, R1, #1		; i++
	B	forTwo
endForTwo
	CMP	R10, #1			; while (swapped=true) 
	BEQ	doWhile	
	B	STOP			; without this line the program tries to read the next line as an instruction and doesn't work.
UNSORT	DCD	6,2,1,9,4,5,3,7,8,0	; This is not an instruction
STOP	B	STOP	
	END
