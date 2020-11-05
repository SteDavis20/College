;
; CS1022 Introduction to Computing II 2019/2020
; eTest Group 1 Q1
;

	AREA	globals, DATA, READWRITE

array	SPACE	1024


	AREA	RESET, CODE, READONLY
	ENTRY

	; initialise the system stack pointer
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

	BL	remove_dups


STOP	B	STOP

;
; Initial data
;
init_array
	DCD	6, 2, 7, 9, 1, 3, 2, 6, 9, 1	; test array elements

N	DCD	10				; test array size (in words)

;
; Your subroutine goes here
;

; remove_dups subroutine
; Removes duplicate elements from an array and returns the new size of the array
; Parameters:
;	R0 - start address of array
;	R2 - number of elements in the array
; Return Value:
;	N (number of elements in array)

remove_dups
	PUSH	{R7-R11}
	
	LDR	R7, =0		; i=0
for
	SUB	R8, R2, #1	; N-1
	CMP	R7, R8		; i < N - 1
	BHS	endFor
	ADD	R9, R7, #1	; j = i + 1
while
	CMP	R9, R2		; j < N
	BHS	endWhile
	LDR	R10, [R0, R9, LSL #2]	; array[j]
	LDR	R11, [R0, R7, LSL #2]	; array[i]
	CMP	R10, R11		; if (array[j] == array[i])
	BNE	else
	MOV	R1, R7		; for parameters in remove subroutine to be correct
	BL	remove
	SUB	R2, R2, #1	; N = N -1
	B	while

else
	ADD	R9, R9, #1	; j++
	B	while

endWhile
	ADD	R7, R7, #1	; i++
	B	for		; }

endFor
	POP	{R5-R9}
	B	STOP

; -----------------------------------------------

; remove subroutine
; Removes an element from an array of word size values
; Parameters:
;   R0 - start address of array
;   R1 - index of element to remove
;   R2 - number of elements in the array
; Return Value:
;   none
remove
	PUSH	{R4-R6}

	ADD	R4, R1, #1
whRemove
	CMP	R4, R2
	BHS	eWhRemove
	LDR	R5, [R0, R4, LSL #2]
	SUB	R6, R4, #1
	STR	R5, [R0, R6, LSL #2]
	ADD	R4, R4, #1
	B	whRemove
eWhRemove
	POP	{R4-R6}
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
