package com.zltech.compose_view

import androidx.compose.foundation.*
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.*

/**
 * 设置页，itemView
 */
@Composable
fun CommonItemComposeView(
    title: String,
    titleColor: Long = 0xff282729,
    content: String = "",
    contentColor: Long = 0xff999999,
    arrowContent: String = "",
    arrowContentColor: Long = 0xff999999,
    showDivider: Boolean = false,
    dividerColor: Long = 0xFFeeeeee,
    onClick: () -> Unit,
    titleFontWeight: FontWeight? = null
) {
    val bottomPadding = if (showDivider) 0.dp else 15.dp
    Column(modifier = Modifier
        .background(color = Color.White)
        .padding(start = 15.dp, top = 15.dp, end = 15.dp, bottom = bottomPadding)
        .clickable(interactionSource = MutableInteractionSource(), indication = null, onClick = onClick)
        .fillMaxWidth()) {
        Row(modifier = Modifier
            //.height(height)
            .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = title,
                fontSize = 15.sp,
                color = Color(titleColor), fontWeight = titleFontWeight)

            Row(verticalAlignment = Alignment.CenterVertically) {
                if (arrowContent.isNotEmpty()) {
                    Text(text = arrowContent,
                        color = Color(arrowContentColor),
                        modifier = Modifier.padding(end = 8.dp))
                }
                Icon(painter = painterResource(id = R.mipmap.common_ic_menu_go),
                    contentDescription = "arrow",
                    tint = Color(0xffd5d5d5),

                )
            }
        }

        //content显示
        if (content.isNotEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp),
                contentAlignment = Alignment.CenterStart) {
                Text(
                    text = content,
                    fontSize = 13.sp, color = Color(contentColor),
                    textAlign = TextAlign.Start
                )
            }
        }

        //divider 显示
        if (showDivider) {
            Divider(color = Color(dividerColor), modifier = Modifier.padding(top = 15.dp))
        }
    }

}

@Preview
@Composable
fun PreviewCommonItemComposeView() {
    CommonItemComposeView(title = "隐私条款",
        content = "用于完成安全保障、推荐信息以及基于地址位，用于完成安全保障、推荐信息以及基于地址位，用于完成安全保障、推荐信息以及基于地址位",
        arrowContent = "已允许",
        onClick = {})
}