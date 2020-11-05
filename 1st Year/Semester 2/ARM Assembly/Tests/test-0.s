;
; CS1022 Introduction to Computing II 2019/2020
; eTest Group 1 Q1
;

N	EQU	9


	AREA	globals, DATA, READWRITE

array	SPACE	1024


	AREA	RESET, CODE, READONLY
	ENTRY

	; initialise the system stack pointer
	LDR	SP, =0x40010000


	LDR	R4, =array
	LDR	R5, =N	
	LDR	R10, =0		; a register containing 0

	;
	; Your program goes here
	; (i.e. your translation of the pseudocode provided
	;
	
;	f o r ( i = 0 ; i < N; i ++) {
; 		f o r ( j = 0 ; j < N; j ++) {
; 			i f ( j >= i + 1 ) {
; 				a r r a y [ i , j ] = ( i * N) + j ;
; 			}
; 			e l s e {
; 				a r r a y [ i , j ] = 0 ;
; 			}
; 		}
; 	}

	LDR 	R6, =0		; i=0
	
forOne
	CMP	R6, R5		; i < N
	BHS	endForOne	
	LDR	R7, =0		; j = 0
forTwo
	CMP	R7, R5		; j<N
	BHS	endForTwo
	ADD	R8, R6, #1	; i+1
	CMP	R7, R8		; if (j >= i + 1)
	BLO	else
	MUL	R9, R6, R5	; x = row * rowSize, i.e. i * N
	ADD	R9, R9, R7	; element = x + column, i.e. (i* N) + j
	STR	R9, [R4, R9, LSL #2]
	B	incrementJ

else
	STR	R10, [R4, R9, LSL #2]	; array[i,j] = 0

incrementJ
	ADD	R7, R7, #1	; j++
	B	forTwo		; }

endForTwo
	ADD	R6, R6, #1		;i++
	B	forOne			; }

endForOne
STOP	B	STOP


	END
