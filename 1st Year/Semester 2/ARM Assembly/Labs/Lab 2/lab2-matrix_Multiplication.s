;
; CS1022 Introduction to Computing II 2018/2019
; Lab 2 - Matrix Multiplication
;

N	EQU	4		

	AREA	globals, DATA, READWRITE

; result array
ARR_R	SPACE	N*N*4		; N*N words (4 bytes each)


	AREA	RESET, CODE, READONLY
	ENTRY

	; initialize system stack pointer (SP)
	LDR	SP, =0x40010000

	;
	; write your matrix multiplication program here
	;

	MOV	R2, #N
	LDR	R4, =0				; i = 0;
	LDR	R6, =0				; k = 0;
	LDR	R11, =ARR_A
	LDR	R12, =ARR_B
	LDR	R3, =ARR_R

forI						;for ( i = 0 ; i < N; i ++) {
	CMP	R4, R2		; i < N
	BHS	endForI				;	for ( j = 0 ; j < N; j ++) {
	LDR	R5, =0		; j=0;
forJ			
	CMP	R5, R2		; j < N
	BHS	endForJ					
	LDR	R7, =0				;		r = 0 ;
	LDR	R6, =0		; k=0
forK						
	CMP	R6, R2		; k < N
	BHS	endForK				;		for ( k = 0 ; k < N; k++) {
	
	; Access index at A[i,k] r8 = index
	MUL	R8, R4, R2	;(row * rowSize);			r = r + ( A[ i , k ] * B[ k , j ] ) ;
	ADD	R8, R8, R6	; ^^ + column	;		}
	LDR	R8, [R11, R8, LSL #2]
	
	; Access index at B[k,j]
	MUL	R9, R6, R2	; (row * rowSize) 
	ADD	R9, R9, R5	; ^^ + column
	LDR	R9, [R12 , R9, LSL #2]

	MUL	R10, R8, R9			; ( A[ i , k ] * B[ k , j ] )
	ADD	R7, R7, R10			; r = r + ( A[ i , k ] * B[ k , j ] ) ;			
	ADD	R6, R6, #1		; k++
	B	forK
endForK
	; Access index at R[i,j]
	MUL	R1, R4, R2	; (row * rowSize)	
	ADD	R1, R1, R5	; ^^ + column	;	}	
	STR	R7, [R3, R1, LSL #2]			;		R[ i , j ] = r ;
	ADD	R5, R5, #1		; j++
	B	forJ
	
endForJ
	ADD	R4, R4, #1		; i++
	B	forI				; }
endForI

STOP	B	STOP


;
; test data
;

ARR_A	DCD	 1,  2,  3,  4		; row 1
	DCD	 5,  6,  7,  8		; row 2
	DCD	 9, 10, 11, 12		; row 3
	DCD	13, 14, 15, 16		; row 4

ARR_B	DCD	 1,  2,  3,  4		; row 1
	DCD	 5,  6,  7,  8		; row 2
	DCD	 9, 10, 11, 12		; row 3
	DCD	13, 14, 15, 16		; row 4

	END
;		Solution Matrix R
;		 90, 100, 110, 120	=	 5A,  64,  6E,  78
;		202, 228, 254, 280	=	 CA,  E4,  FE, 118
;		314, 356, 398, 440	=	13A, 164, 18E, 1B8
;		426, 484, 542, 600	=	1AA, 1E4, 21E, 258