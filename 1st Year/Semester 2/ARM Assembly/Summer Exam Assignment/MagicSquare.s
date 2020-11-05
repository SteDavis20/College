;
; CS1022 Introduction to Computing II 2018/2019
; Magic Square
;

	AREA	RESET, CODE, READONLY
	ENTRY

	; initialize system stack pointer (SP)
	LDR	SP, =0x40010000

	; Below are 3 different square arrays, to test any of them, comment out the other 2.
	; tests a 3x3 square array (isMagic)
	LDR	R0, =arr1
	LDR	R1, =size1
	LDR	R2, [R1]

	; tests a 4x4 square array (notMagic)
	;LDR	R0, =arr2
	;LDR	R1, =size2
	;LDR	R2, [R1]	
	
	; tests a 5x5 square array (isMagic)
	;LDR	R0, =arr3
	;LDR	R1, =size3
	;LDR	R2, [R1]
	;
	; test your subroutine here
	; if square is Magic R0 = 1, if not Magic R0 = 0.
	BL	isMagic		; isMagic(n,magicConstant);
	LDR	R0, =1		; isMagicSquare = true;
	B	endNow
notMagic
	LDR	R0, =0		; isMagicSquare = false;
endNow
stop	B	stop
;-----------------------------------------------------------------------------------------------------------------------------
; getMagicConstant
; Sets the sum of the first row as the magic constant. Regardless of whether this value is the magicConstant or not, if the
; other sums do not equal this value, then the square is not Magic. 
; parameters:
;	r2 - n (dimension of square)
; result:
;	r3 - magicConstant (sum of first Row acting as magic constant)
getMagicConstant
	PUSH	{R4-R8,lr}
	MOV	R4, R2		; dimension of square
	
	LDR	R5, =0		; int i = 0;
	LDR	R6, =0		; int j = 0;
	LDR	R7, =0		; int magicConstant = 0;
for				; for(int j=0; j<n; j++) {
	CMP	R6, R4		; if(j<n)
	BHS	endFor
	MUL	R8, R5, R4	; 	temp = row*rowSize
	ADD	R8, R8, R6	; 	array[i,j] = temp + column
	LDR	R8, [R0, R8, LSL #2]	; load index value from memory address
	ADD	R7, R7, R8	; 	magicConstant+=array[i,j]
	ADD	R6, R6, #1	; 	j++
	B	for		; }
endFor
	MOV	R3, R7		; return/store MagicConstant
	POP	{R4-R8,pc}
	BX	lr
;-----------------------------------------------------------------------------------------------------------------------------
; testRows
; Checks if the sum of each row is equal to the magic constant.
; parameters:
;	r2 - n (dimension of square)
;	r3 - magicConstant
; result:
;	void
;
testRows
	PUSH	{R4-R9,lr}
	
	MOV	R4, R2		; dimension of square (width and/or height)
	MOV	R5, R3		; magicConstant;
	
	LDR	R7, =1		; int i = 1;
rowsOuterFor			; for(int i=1; i<n; i++) {
	LDR	R6, =0		; 	int sum = 0;
	CMP	R7, R4		; 	(i < n)
	BHS	endRowsLoops
	LDR	R8, =0		; 	int j = 0;
rowsInnerFor			; 	for(int j=0; j<n; j++) {
	CMP	R8, R4		; 		(j < n)
	BHS	endRowsInner
	MUL	R9, R7, R4	; 		temp = row*rowSize
	ADD	R9, R9, R8	; 		array[i,j] = temp + column
	LDR	R9, [R0, R9, LSL #2]	; 	load value of array[i,j] from memory address
	ADD	R6, R6, R9	; 		sum += array[i,j]
	ADD	R8, R8, #1	; 		j++
	B	rowsInnerFor	; 	}
endRowsInner
	CMP	R6, R5		; 	if(sum == magicConstant) {
	BNE	notMagic
	ADD	R7, R7, #1	; 		i++;
	B	rowsOuterFor	; }
endRowsLoops
	POP	{R4-R9,pc}
	BX	lr
;----------------------------------------------------------------------------------------------------------------------------
; testColumns
; Checks if the sum of each column is equal to the magic constant.
; parameters:
;	r2 - n (dimension of square)
;	r3 - magicConstant
; result:
;	void
;
testColumns
	PUSH	{R4-R9,lr}
	
	MOV	R4, R2		; dimension of square (width and/or height)
	MOV	R5, R3		; magicConstant;
	
	LDR	R8, =0		; int j = 0;
columnsOuterFor			; for(int j=0; j<n; j++) {
	LDR	R6, =0		; 	int sum = 0;
	CMP	R8, R4		; 	(j < n)
	BHS	endColumnsLoops
	LDR	R7, =0		; 	int i = 0;
columnsInnerFor			; 	for(int i=0; i<n; i++) {
	CMP	R7, R4		; 		(i < n)
	BHS	endColumnsInner
	MUL	R9, R7, R4	; 		temp = row*rowSize
	ADD	R9, R9, R8	; 		array[i,j] = temp + column
	LDR	R9, [R0, R9, LSL #2]	; 	load value of array[i,j] from memory address
	ADD	R6, R6, R9	; 		sum += array[i,j]
	ADD	R7, R7, #1	; 		i++
	B	columnsInnerFor	; 	}
endColumnsInner
	CMP	R6, R5		; if(sum == magicConstant) {
	BNE	notMagic
	ADD	R8, R8, #1	; 	j++;
	B	columnsOuterFor ; }
endColumnsLoops
	POP	{R4-R9,pc}
	BX	lr
;----------------------------------------------------------------------------------------------------------------------------
; testTopLeftMainDiagonal
; Checks if the sum of the main diagonal from the top left to the bottom right is equal to the magic constant.
; parameters:
;	r2 - dimension of square (width and/or height)
;	r3 - magicConstant
; result:
;	void
;
testTopLeftMainDiagonal
	PUSH	{R4-R9,lr}
	
	MOV	R4, R2		; dimension of square (width and/or height)
	MOV	R5, R3		; magicConstant;
	
	LDR	R6, =0		; int sum = 0;
	LDR	R8, =0		; int j = 0;
	LDR	R7, =0		; int i = 0;
topLeftFor			; for(int i=0; i<n; i++) {
	CMP	R7, R4		; 	(i<n)
	BHS	endTopLeftFor		
	MUL	R9, R7, R4	; 	temp = row*rowSize
	ADD	R9, R9, R8	; 	array[i,j] = temp + column
	LDR	R9, [R0, R9, LSL #2]  ; load value of array[i,j] from memory address
	ADD	R6, R6, R9	; 	sum += array[i,j]
	ADD	R8, R8, #1	; 	j++
	ADD	R7, R7, #1	; 
	B	topLeftFor	; }
endTopLeftFor
	CMP	R6, R5		; if(sum != magicConstant) {
	BNE	notMagic
	POP	{R4-R9,pc}
	BX	lr
;-----------------------------------------------------------------------------------------------------------------------------
; testTopRightMainDiagonal
; Checks if the sum of the main diagonal from the top right to the bottom left is equal to the magic constant.
; parameters:
;	r2 - dimension of square (width and/or height)
;	r3 - magicConstant
; result:
;	void
;
testTopRightMainDiagonal
	PUSH	{R4-R9,lr}
	
	MOV	R4, R2		; dimension of square (width and/or height)
	MOV	R5, R3		; magicConstant;
	
	LDR	R6, =0		; int sum = 0;
	SUB	R8, R4, #1	; j = n - 1;
	LDR	R7, =0		; int i = 0;
topRightFor			; for(int i=0; i<n; i++) {
	CMP	R7, R4		; 	(i < n)
	BHS	endTopRightFor		
	MUL	R9, R7, R4	; 	temp = row*rowSize
	ADD	R9, R9, R8	; 	array[i,j] = temp + column
	LDR	R9, [R0, R9, LSL #2]  ; load value of array[i,j] from memory address
	ADD	R6, R6, R9	; 	sum += array[i,j]
	SUB	R8, R8, #1	; 	j--
	ADD	R7, R7, #1	; 
	B	topRightFor	; }
endTopRightFor
	CMP	R6, R5		; if(sum != magicConstant) {
	BNE	notMagic
	POP	{R4-R9,pc}
	BX	lr
;-----------------------------------------------------------------------------------------------------------------------------
; testRangeOfNumbers
; Checks if the numbers in the square are within the appropriate boundaries. - i.e. between 1 and n^2 inclusive.
; parameters:
;	r2 - dimension of square (width and/or height)
; result:
;	void
;
testRangeOfNumbers
	PUSH	{R4-R10,lr}
	
	MOV	R4, R2		; dimension of square (width and/or height)

	MOV	R10, R4		; copy of n
	MUL	R10, R4, R10	; n^2
	
	LDR	R7, =0;		; int i = 0;
rangeOuterFor			; for(int i=0; i<n; i++) {
	CMP	R7, R4		; 	(i<n)	
	BHS	restoreStack
	LDR	R8, =0		; 	int j = 0;
rangeInnerFor			; 	for(int j=0; j<n; j++) {
	CMP	R8, R4		; 		(j<n)
	BHS	endRangeInner
	MUL	R9, R7, R4	; 		temp = row * rowSize
	ADD	R9, R9, R8	; 		array[i,j] = temp + column
	LDR	R9, [R0, R9, LSL #2]
	CMP	R9, R10		; 		if (array[i,j] > n^2) {
	BHI	notMagic	; 		}
	CMP	R9, #1		; 		if (array[i,j] < 1) {
	BLO	notMagic	; 		}
	ADD	R8, R8, #1	; 
	B	rangeInnerFor	; 	}
endRangeInner
	ADD	R7, R7, #1	; 
	B	rangeOuterFor	; }
	
restoreStack
	POP	{R4-R10,pc}
	BX	lr
;-----------------------------------------------------------------------------------------------------------------------------
; testIndividuality
; Checks if all numbers are unique - i.e. no 2 numbers are the same.
; parameters:
;	r2 - dimension of square (width and/or height)
; result:
;	void
;
testIndividuality
	PUSH	{R4-R11,lr}
	
	MOV	R4, R2		; dimension of square (width and/or height)

	LDR	R5, =0		; count = 0;
	LDR	R6, =0		; int i = 0;
forOne				; for(int i=0; i<n; i++) {
	CMP	R6, R4		; 	(i<n)
	BHS	endForOne
	LDR	R7, =0		; 	int j = 0;
forTwo				; 	for(int j=0; j<n; j++) {
	CMP	R7, R4		; 		(j<n) 
	BHS	endForTwo
	;MOV	R8, R6		; i2 = i (avoids comparing numbers which were already compared)
	LDR	R8, =0		; 	i2=0;
forThree			;		for(int i2=0; i2<n; i2++) {
	CMP	R8, R4		; 			(i2<n)
	BHS	endForThree
	;MOV	R9, R7		; j2 = j (avoids comparing numbers which were already compared)
	LDR	R9, =0		; 			j2=0;
forFour				; 			for(int j2=0; j2<n; j2++) {
	CMP	R9, R4		; 				(j2<n)
	BHS	endForFour
	
	; get array[i,j]
	MUL	R10, R6, R4	; 				temp = row * rowSize
	ADD	R10, R10, R7	; 				array[i,j] = temp + column
	LDR	R10, [R0, R10, LSL #2]	; 			load value from memory address
	
	; get array[i2,j2]
	MUL	R11, R8, R4	; 				temp = row * rowSize
	ADD	R11, R11, R9	; 				array[i2,j2] = temp + column
	LDR	R11, [R0, R11, LSL #2]	; 			load value from memory address
	
	CMP	R10, R11	; 				if(array[i,j] == array[i2,j2]) {
	BNE	notEqual
	ADD	R5, R5, #1	; 				count++
	CMP	R5, #1		; 					if (count < 1) {
	BHI	notMagic	
notEqual
	ADD	R9, R9, #1	; 
	B	forFour		;			}
endForFour
	ADD	R8, R8, #1	; 
	B	forThree	;		}
	
endForThree
	ADD	R7, R7, #1	; 
	LDR	R5, =0		; count = 0;
	B	forTwo		;	}

endForTwo
	ADD	R6, R6,	#1	; 
	B	forOne		; }

endForOne
	POP	{R4-R11,pc}
	BX	lr
;-----------------------------------------------------------------------------------------------------------------------------
; isMagic
; Determines whether a square two-dimensional array in memory is a Magic Square.
; parameters:
;	n/a
; result:
;	void
;
isMagic
	PUSH	{lr}
	BL	getMagicConstant		; getMagicConstant(n) - n represents width and/or height of square
	BL	testRows			; testRows(n, magicConstant) 
	BL	testColumns			; testColumns(n, magicConstant)
	BL	testTopLeftMainDiagonal		; testTopLeftMainDiagonal(n, magicConstant)
	BL	testTopRightMainDiagonal	; testTopRightMainDiagonal(n, magicConstant)
	BL	testRangeOfNumbers		; testRangeOfNumbers(n)
	BL	testIndividuality		; testIndividuality(n)
	POP	{lr}
	BX	lr
;-----------------------------------------------------------------------------------------------------------------------------
size1	DCD	3				; a 3x3 array
arr1	DCD	8,1,6				; the array
	DCD	3,5,7
	DCD	4,9,2

size2	DCD	4				; a 4x4 array
arr2	DCD	16,3,2,13			; the array
	DCD	5,10,11,8
	DCD	9,6,1,12			; change to "9,6,7,12" to make Magic
	DCD	4,15,14,1
		
size3	DCD	5				; a 5x5 array
arr3	DCD	17,24,1,8,15			; the array
	DCD	23,5,7,14,16
	DCD	4,6,13,20,22
	DCD	10,12,19,21,3
	DCD	11,18,25,2,9

	END
;-----------------------------------------------------------------------------------------------------------------------------
