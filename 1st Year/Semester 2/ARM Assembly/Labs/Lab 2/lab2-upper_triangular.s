;
; CS1022 Introduction to Computing II 2018/2019
; Lab 2 - Upper Triangular
;

N	EQU	5		

	AREA	RESET, CODE, READONLY
	ENTRY

	; initialize system stack pointer (SP)
	LDR	SP, =0x40010000

	;
	; write your program here to determine whether ARR_A
	;   (below) is a matrix in Upper Triangular form.
	;
	; Store 1 in R0 if the matrix is in Upper Triangular form
	;   and zero otherwise.
	;
	
	
	; int originalI = 1;
	; int j = 0;
	
	; adjustI
	; int i = originalI + j;
	
	; for (int i = originalI + j; i < N; i++) {
	;	if ( A[i,j] != 0) {
	;		R0 = 0;
	;	}
	; i++;
	; }
	; B jCheck
	
	; jCheck
	; if (j<N) {
	;	j++;
	;	B adjustI
	; }
	; else {
	;	stop B stop 
	; }
	
; stop B stop	
	
	LDR	R4, =1		; int originalI = 1;
	LDR	R5, =0		; int j = 0;
	LDR	R6, =N		; size of rows & columns
	LDR	R7, =ARR_A	; start address of Matrix

adjustI
	ADD	R8, R4, R5	; int i = originalI + j;

for				; for (int i = originalI + j; i < N; i++) {
	CMP	R8, R6		; (i<N)
	BHS	jCheck
	MUL	R9, R8, R6	; x = (row*rowSize);
	ADD	R9, R9, R5	; index = x + column;
	LDR	R9, [R7, R9, LSL #2]	; load index value from memory
	CMP	R9, #0
	BNE	notUpperTriangular
	ADD	R8, R8, #1	; i++;
	B	for		; }

jCheck
	CMP	R5, R6		; if (j<N) 
	BHS	upperTriangular	; {	
	ADD	R5, R5, #1	; 	j++;
	B	adjustI		; }


notUpperTriangular
	LDR	R0, =0	
	B	STOP
upperTriangular
	LDR	R1, =1
	
STOP	B	STOP


;
; test data
;

ARR_A	DCD	 1,  2,  3,  4, 8
	DCD	 0,  6,  7,  8, 9
	DCD	 0,  0, 11, 12, 7
	DCD	 0,  0,  0, 16, 6
	DCD	 0,  0,  0,  0, 5

	END
