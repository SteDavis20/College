;
; CSU11021 Introduction to Computing I 2019/2020
; UPPER CASE echo to console
;

	AREA	RESET, CODE, READONLY
	ENTRY

	BL	inithw		; Get ready for console I/O

doGet
	BL	get
		
	; a = 0x61,	z = 0x7A
	; A = 0x41, Z = 0x5A
	; to convert: SUB 0x20
	
	; R0 = userInput
	; "BL get" gets the user's input
	; if R0 >= a AND R0 <= z, R0 = R0 - 0x20, (subtracting #('a' - 'A') reads better.
	
	; R0 = userInput
	
	CMP R0, #'a'		; if userInput >= a
	BLO endNow			; if userInput < a, branch to endNow
	CMP R0, #'z'		; if userInput <= z
	BHI endNow			; if userInput > z, branch to endNow
	SUB R0, R0, #('a' - 'A')	; Converting lower case userInput to UPPERCASE	
	
	; The next instruction will send the character code in R0 to
	;  the console, where it will be displayed

	BL	put
	CMP	R0, #0x0D	; Stop when RETURN is entered (ASCII 0x0A)
	BNE	doGet
endNow
STOP	B	STOP

;
; You are free to ignore everything below this line
;

PINSEL0	EQU	0xE002C000
U0RBR	EQU	0xE000C000
U0THR	EQU	0xE000C000
U0LCR	EQU	0xE000C00C
U0LSR	EQU	0xE000C014

;
; inithw subroutines
; performs hardware initialisation, including console
; parameters:
;	none
; return value:
;	none
;
inithw
	LDR	SP, =0x40010000	; initialse SP
	PUSH	{R0-R1}
	LDR	R0, =PINSEL0		; enable UART0 TxD and RxD signals
	MOV	R1, #0x50
	STRB	R1, [R0]
	LDR	R0, =U0LCR		; 7 data bits + parity
	LDR	R1, =0x02
	STRB	R1, [R0]
	POP	{R0-R1}
	BX	LR

;
; get subroutine
; returns the ASCII code of the next character read on the console
; parameters:
;	none
; return value:
;	R0 - ASCII code of the character read on teh console (byte)
;
get	PUSH	{R1}
	LDR	R1, =U0LSR		; R1 -> U0LSR (Line Status Register)
get0	LDR	R0, [R1]		; wait until
	ANDS	R0, #0x01		; receiver data
	BEQ	get0			; ready
	LDR	R1, =U0RBR		; R1 -> U0RBR (Receiver Buffer Register)
	LDRB	R0, [R1]		; get received data
	POP	{R1}
	BX	LR			; return

;
; put subroutine
; writes a character to the console
; parameters:
;	R0 - ASCII code of the character to write
; return value:
;	none
;
put	PUSH	{R1}
	LDR	R1, =U0LSR		; R1 -> U0LSR (Line Status Register)
	LDRB	R1, [R1]		; wait until transmit
	ANDS	R1, R1, #0x20		; holding register
	BEQ	put			; empty
	LDR	R1, =U0THR		; R1 -> U0THR
	STRB	R0, [R1]		; output charcter
put0	LDR	R1, =U0LSR		; R1 -> U0LSR
	LDRB	R1, [R1]		; wait until
	ANDS	R1, R1, #0x40		; transmitter
	BEQ	put0			; empty (data flushed)
	POP	{R1}
	BX	LR			; return

	END
