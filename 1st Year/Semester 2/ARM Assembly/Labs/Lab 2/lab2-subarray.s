;
; CS1022 Introduction to Computing II 2018/2019
; Lab 2 - Subarray
;

N	EQU	7
M	EQU	3		

	AREA	RESET, CODE, READONLY
	ENTRY

	; initialize system stack pointer (SP)
	LDR	SP, =0x40010000

	;
	; Write your program here to determine whether SMALL_A
	;   is a subarray of LARGE_A
	;
	; Store 1 in R0 if SMALL_A is a subarray and zero otherwise
	;

;---------------------------------------------------------

	LDR 	R4, =N		; large array size
	LDR 	R5, =M		; sub array size
	LDR 	R6, =LARGE_A	; start address of large array
	LDR 	R7, =SMALL_A	; start address of subArray
	LDR 	R8, =0		; int i = 0;
	LDR 	R9, =0 		; int j = 0;
	LDR 	R10, =0		; int k = 0;
	LDR 	R11, =0		; int l = 0;

checkI
	MUL	R2, R8, R4	; x = (row*rowSize)
	ADD	R2, R2, R9	; index = x + column
	LDR	R2, [R6, R2, LSL #2]	; A[i,j]
	
	MUL	R3, R10, R5	; x = (row*rowSize)
	ADD	R3, R3, R11	; index = x + column
	LDR	R3, [R7, R3, LSL #2]	; B[k,l]
	
	CMP	R2, R3		; if (A[i,j] == B[k,l]) {
	BNE	firstNotEqual
	
	MOV	R12, R8		; i2 = i;
	MOV	R1, R9		; j2 = j;
	
	; inner loop is below
loop2
	MUL	R2, R12, R4	; x = row * rowSize
	ADD	R2, R2, R1	; index = x + column
	LDR	R2, [R6, R2, LSL #2]	; A[i2,j2]
	
	MUL	R3, R10, R5	; x = row * rowSize
	ADD	R3, R3, R11	; index = x + column
	LDR	R3, [R7, R3, LSL #2]	; B[k,l]
	
	CMP	R2, R3		; if (A[i2,j2] == B[k,l])
	BNE	resetSubArray
	ADD	R10, R10, #1	; k++
	CMP	R10 ,R5		; if (k < M)
	BHS	loop2Else1
	ADD	R12, R12, #1	; 	i2++;
	B	loop2
	
loop2Else1
	ADD	R11, R11, #1	; l++
	CMP	R11, R5		; if (l < M)
	BHS	loop2Else2
	LDR	R10, =0		; k = 0 
	MOV	R12, R8		; i2 = i;
	ADD	R1, R1, #1	; j2++;
	B	loop2
	
loop2Else2
	B	isSubArray
	
firstNotEqual
	ADD	R8, R8, #1
	CMP	R8, R4		; if(i++<N) {
	BHS	else1		; i is already incremented
	B	checkI		; }
	
else1	
	SUB	R8, R8, #1	; undo i increment. i.e. decrement i
	ADD	R9, R9, #1	
	CMP	R9, R4		; if (j++<N)
	BHS	else2
	LDR	R8, =0		; 	i=0;
	B	checkI
	
else2
	SUB	R9, R9, #1
	B	isNotSubArray
	
;-----------------------------------------------------------------------
	
resetSubArray
	LDR	R10, =0		; k = 0;	 Should it be k2 = 0?
	LDR	R11, =0		; l = 0;	 Should it be l2 = 0?
	
	ADD	R8, R8, #1	; i++
	CMP	R8, R4		; if (i++<N)
	BHS	incrementJ
	B	checkI
incrementJ
	ADD	R9, R9, #1	; j++
	CMP	R9, R4		; if (j++<N)
	BHS	else2
	LDR	R8, =0		; 	i=0;
	B	checkI
		
isNotSubArray
	LDR	R0, =0
	B	STOP	

isSubArray
	LDR	R0, =1

STOP	B	STOP

;------------------------------------------------------

;
; test data
;

LARGE_A	DCD	 48, 37, 15, 44,  3, 17, 26
	DCD	  2,  9, 12, 18, 14, 33, 16
	DCD	 13, 20,  1, 22,  7, 48, 21
	DCD	 27, 19, 44, 49, 41, 17, 10
	DCD	 29, 17, 22,  4,  6, 43, 41
	DCD	 37, 35, 38, 24, 16, 25,  0
	DCD	 17,  0, 48, 15, 27, 35, 11

SMALL_A	DCD	 49, 41, 17
	DCD	  4,  6, 43
	DCD	 24, 16, 25

	END
