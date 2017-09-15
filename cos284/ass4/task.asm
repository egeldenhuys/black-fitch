section .start
    global get_non_volatile
    global set_non_volatile
    global get_rsp
    global get_rbp

; -----------------------------
; get_non_volatile
; ----------------
;   DESCRIPTION:
;       Returns the non-volatile registers in the given array
;   ARGS:
;       rdi - long[5]
;   RETURNS:
;       The values in the given array
get_non_volatile:
    push rbp
    mov rbp, rsp

    mov qword [rdi], rbx
    mov qword [rdi + 1*8], r12
    mov qword [rdi + 2*8], r13
    mov qword [rdi + 3*8], r14
    mov qword [rdi + 4*8], r15

    pop rbp
    ret

; -----------------------------
; set_non_volatile
; ----------------
;   DESCRIPTION:
;       Sets the non volatile registers
;   ARGS:
;       rdi - long[5]
;   RETURNS:
;       nothing
set_non_volatile:
    push rbp
    mov rbp, rsp

    mov rbx, qword [rdi]
    mov r12, qword [rdi + 1*8]
    mov r13, qword [rdi + 2*8]
    mov r14, qword [rdi + 3*8]
    mov r15, qword [rdi + 4*8]

    pop rbp
    ret

; -----------------------------
; get_rsp
; ----------------
;   DESCRIPTION:
;       Returns the value of rsp outside this function
;   ARGS:
;       none
;   RETURNS:
;       rax - the value of rsp
; outside +24
; RET + 16
get_rsp:
    push rbp ; +8
    mov rbp, rsp

    mov rcx, rsp
    add rcx, 24 ; offset

    mov rax, rcx

    pop rbp
    ret

; -----------------------------
; get_rbp
; ----------------
;   DESCRIPTION:
;       Returns the value of rbp outside this function
;   ARGS:
;       none
;   RETURNS:
;       rax - the value of rbp
; outside +24
; RET + 16
get_rbp:
    mov rax, rbp
    ret
