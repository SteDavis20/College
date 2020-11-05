;
; CS1022 Introduction to Computing II 2019/2020
; Sample eTest Q1
;

N	EQU	9


	AREA	globals, DATA, READWRITE

array	SPACE	1024


	AREA	RESET, CODE, READONLY
	ENTRY

	LDR	R4, =array
	LDR	R5, =N	
	LDR	R8, =0		; simply a register containing value 0
	
	LDR R6, =0		; i = 0
	
forOne				; for ( i = 0 ; i < N; i ++) 
	CMP	R6, R5		; 
	BHS	endForOne	; {
	LDR 	R7, =0		; j = 0
forTwo				;	for ( j = 0 ; j < N; j ++) 
	CMP	R7, R5		; 
	BHS	endForTwo	; 	{
	MUL	R9, R6, R5	; x = row * rowSize
	ADD	R9, R9, R7	; element = x + column
	CMP	R6, R7		; 		if (i ==j) 
	BNE	elseIf		; 		{
	STR	R8, [R4, R9, LSL #2]	;		array [ i , j ] = 0 ;
	ADD	R7, R7, #1	; j++
	B	forTwo		;		}
	
elseIf
	CMP	R6, R7		; 		else if (i>j)
	BLS	else		;		{
	SUB	R10, R6, R7	;			i-j;
	STR	R10, [R4, R9, LSL #2] ; 		array [ i , j ] = i - j 			
	ADD	R7, R7, #1	; j++
	B	forTwo		;		}

else				;		else {
	SUB	R10, R7, R6	; j-i
	STR	R10, [R4, R9, LSL #2]	;		array [ i , j ] = j - i ;
	ADD	R7, R7, #1	; j++
	B	forTwo			      ; }

endForTwo
	ADD	R6, R6, #1		; i++
	B	forOne			; }
	
endForOne
STOP	B	STOP


	END
