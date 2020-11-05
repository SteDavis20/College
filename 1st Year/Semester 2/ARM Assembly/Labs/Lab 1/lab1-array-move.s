;
; CS1022 Introduction to Computing II 2018/2019
; Lab 1 - Array Move
;

N	EQU	16		; number of elements

	AREA	globals, DATA, READWRITE

; N word-size values

ARRAY	SPACE	N*4		; N words


	AREA	RESET, CODE, READONLY
	ENTRY

	; for convenience, let's initialise test array [0, 1, 2, ... , N-1]

	LDR	R0, =ARRAY
	LDR	R1, =0		
L1	CMP	R1, #N
	BHS	L2
	STR	R1, [R0, R1, LSL #2] ;  R1 = Memory.Word[R0 + R1 * 4]
	ADD	R1, R1, #1
	B	L1
L2

	; initialise registers for your program

	LDR	R0, =ARRAY
	LDR	R1, =6		; old index of element to move.
	LDR	R2, =3		; destination index to which the element should be moved.
	LDR	R3, =N		; The number of elements in the array.
	
	; 1. Copy original value at old index in array into register.
	; 2. Move the range of values from "destination index" to "old index" -1, one place to the right.
	; 3. Store original value of old index from step 1 into destination index.
	
	LDR	R4, [R0, R1, LSL #2]		; copy original value at old index
	SUB	R5, R1, #1			; i=(oldIndex-1)	
for						; for (i=old index-1; i>=destination index; i--) {
	CMP	R5, R2				;	array[i] = array[i++];
	BLO	endFor				; }
	LDR	R6, [R0, R5, LSL #2]	; elem at index i
	ADD	R5, R5, #1		; index++, new position for index, i.e. one to the right
	STR	R6, [R0, R5, LSL #2]	; change value in memory
	SUB	R5, R5, #2		; set i = to next index you wish to move
	B	for
endFor	
	STR	R4, [R0, R2, LSL #2]	; Place original value at old index (6) into destination index (3)
	
STOP	B	STOP

	END
