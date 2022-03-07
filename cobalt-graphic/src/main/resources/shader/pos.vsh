#version 460 core

layout (location = 0) in vec2 pos;
layout (location = 1) in vec3 m_color;

out vec3 color;

void main(){
    color = m_color;
    gl_Position = vec4(pos,0.5f,1.0f);
}