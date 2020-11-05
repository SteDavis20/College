;
; CS1022 Introduction to Computing II 2019/2020
; Sample eTest Q2
;

	AREA	globals, DATA, READWRITE

array	SPACE	1024


	AREA	RESET, CODE, READONLY
	ENTRY

	;
	; initialise the system stack pointer
	;
	LDR	SP, =0x40010000

	;
	; initialise array in RAM by copying from init_array
	;

	LDR	R0, =array	; destination in RAM		
	LDR	R1, =init_array	; source in ROM
	LDR	R4, =N
	LDR	R2, [R4]	; size of array
	BL	copy_arr	; initialise


	;
	; Your program to test your subroutine goes here
	;
	
	LDR	R3, [R4]	; size of array
	BL	sort		; sort(array, N)
	
	

STOP	B	STOP

;
; Initial data
;
init_array
	DCD	6, 2, 7, 9, 1, 3, 2, 6, 9, 1	; test array elements

N	DCD	10				; test array size (in words)

; -----------------------------------------------------------------------------
; Your subroutine goes here

; void sort(array , N)
; {
; 	do
; 	{
; 		swapped = false;
; 		for (i=1; i<N; i++)
; 		{
; 			if(array[i-1]<array[i])
; 			{
; 				swap (array, i, i-1);
; 				swapped = true;
; 			}
; 		}
; 	} while (swapped);
; }

; sort subroutine - my subroutine
; sorts the elements of an array in descending order
; Parameters:
;	R0 - start address of array
;	R3 - size of Array
; Return Value:
;	none
sort
	PUSH	{R6-R10}
	
doWhile				; do
				; {
	LDR	R9, =0		; 	swapped = false;
	
	LDR	R10, =1		; i=1;
for
	CMP	R10, R3		; i<N;
	BHS	endFor
	SUB	R6, R10, #1	; i-1;
	LDR	R7, [R0, R6, LSL #2] ; array[i-1]
	LDR	R8, [R0, R10, LSL #2] ; array[i]
	CMP	R7, R8		; if (array[i-1] < array[i]) {
	BHS	incrementI
	BL	swap		; swap (array, i, i-1);
	LDR	R9, =1		; swapped = true
	
incrementI
	ADD	R10, R10, #1	; i++
	B	for
	
endFor
	CMP	R9, #1		; } while (swapped);
	BNE	endDoWhile	
	B	doWhile		; 

endDoWhile
	POP	{R4-R8}		; remove values
	B	STOP
	;BX	LR		; branch back to link register (where function was called)

; -----------------------------------------------------------------------------

; swap subroutine - Given in Question
; Swaps two elements in an array of word size values
; Parameters:
;   R0 - start address of array
;   R1 - index of first element to swap
;   R2 - index of second element to swap
; Return Value:
;   none
swap
	MOV	R1, R10		; i-1;
	MOV	R2, R6		; i;
	
	PUSH	{R4, R5}
	LDR	R4, [R0, R1, LSL #2]
	LDR	R5, [R0, R2, LSL #2]
	STR	R5, [R0, R1, LSL #2]
	STR	R4, [R0, R2, LSL #2]
	POP	{R4, R5}
	BX	LR


; copy_arr subroutine
; Copies an array of words in memory
; Parameters:
;   R0 - destination address
;   R1 - source address
;   R2 - number of words to copy
; Return Value:
;   none
copy_arr
	PUSH	{R4-R5}
	LDR	R4, =0
wh_copy_arr
	CMP	R4, R2
	BHS	ewh_copy_arr
	LDR	R5, [R1, R4, LSL #2]
	STR	R5, [R0, R4, LSL #2]
	ADD	R4, R4, #1
	B	wh_copy_arr
ewh_copy_arr
	POP	{R4-R5}
	BX	LR

	END
